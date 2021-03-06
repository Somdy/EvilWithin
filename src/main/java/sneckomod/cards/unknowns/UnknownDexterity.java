package sneckomod.cards.unknowns;

import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.NewExpr;

import java.util.function.Predicate;

public class UnknownDexterity extends AbstractUnknownCard {
    public final static String ID = makeID("UnknownDexterity");
    public static boolean bruh = false;

    public UnknownDexterity() {
        super(ID, CardType.SKILL, CardRarity.UNCOMMON);
    }

    public boolean useCheck(AbstractCard card) {
        bruh = false;
        try {
            ClassPool pool = Loader.getClassPool();
            CtClass ctClass = pool.get(card.getClass().getName());
            ctClass.defrost();
            CtMethod useMethod;
            try {
                useMethod = ctClass.getDeclaredMethod("use");
            } catch (NotFoundException ignore) {
                // This card doesn't have a `use` method, skip it
                return false;
            }

            useMethod.instrument(new ExprEditor() {
                @Override
                public void edit(NewExpr e) {
                    try {
                        CtConstructor ctConstructor = e.getConstructor();
                        CtClass cls = ctConstructor.getDeclaringClass();
                        if (cls != null) {
                            CtClass parent = cls;
                            do {
                                parent = parent.getSuperclass();
                            } while (parent != null && !parent.getName().equals(AbstractPower.class.getName()));
                            if (parent != null && parent.getName().equals(AbstractPower.class.getName())) {
                                if (ctConstructor.getDeclaringClass().getName().equals(DexterityPower.class.getName()))
                                    bruh = true;
                            }
                        }
                    } catch (NotFoundException ignored) {
                    }
                }
            });
        } catch (NotFoundException | CannotCompileException e) {
            e.printStackTrace();
        }
        return bruh;
    }

    @Override
    public Predicate<AbstractCard> myNeeds() {
        return this::useCheck;
    }
}