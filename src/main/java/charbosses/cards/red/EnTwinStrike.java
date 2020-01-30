package charbosses.cards.red;

import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;

import charbosses.cards.AbstractBossCard;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

public class EnTwinStrike extends AbstractBossCard
{
    public static final String ID = "EvilWithin_Charboss:Twin Strike";
    private static final CardStrings cardStrings;
    
    public EnTwinStrike() {
        super(ID, EnTwinStrike.cardStrings.NAME, "red/attack/twin_strike", 1, EnTwinStrike.cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.RED, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 5;
        this.tags.add(CardTags.STRIKE);
    }
    
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot(new DamageAction(p, new DamageInfo(m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new DamageAction(p, new DamageInfo(m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }
    
    @Override
    public int getValue() {
    	return super.getValue() * 2;
    }
    
    @Override
    public AbstractCard makeCopy() {
        return new EnTwinStrike();
    }
    
    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Twin Strike");
    }
}