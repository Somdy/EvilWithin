package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theHexaghost.powers.FuturePower;

public class GhostOfSpireFuture extends AbstractHexaCard {

    public final static String ID = makeID("GhostOfSpireFuture");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public GhostOfSpireFuture() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new FuturePower(magicNumber, 1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_MAGIC);
        }
    }
}