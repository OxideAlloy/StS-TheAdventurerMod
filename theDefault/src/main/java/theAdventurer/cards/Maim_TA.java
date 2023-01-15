package theAdventurer.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.SkewerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAdventurer.TheAdventurerMod;
import theAdventurer.actions.MaimAction;
import theAdventurer.characters.TheAdventurer;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class Maim_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(Maim_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("Maim_TA.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = -1;
    private static final int DAMAGE = 7;
    private static final int AMOUNT = 1;
    //private static boolean ISUPGRADED = false;
    //private static final int UPGRADED_AMOUNT = 2;
    //private static final int DEBUFF = 1;
    //private static final int UPGRADED_DEBUFF = 1;

    public Maim_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = AMOUNT;
        //defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DEBUFF;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            this.addToBot(new MaimAction(p, m, this.damage, this.magicNumber, this.damageTypeForTurn, this.freeToPlayOnce, this.upgraded,this.energyOnUse));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //this.upgradeMagicNumber(UPGRADED_AMOUNT);
            //this.upgradeDefaultSecondMagicNumber(UPGRADED_DEBUFF);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
