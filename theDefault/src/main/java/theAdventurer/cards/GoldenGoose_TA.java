package theAdventurer.cards;

import basemod.AutoAdd;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAdventurer.TheAdventurerMod;
import theAdventurer.characters.TheAdventurer;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class GoldenGoose_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(GoldenGoose_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("GoldenGoose_TA.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 1;

    public GoldenGoose_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    //// START GOLDEN CODE ////
    public void atTurnStart() {
        this.baseDamage = AbstractDungeon.player.gold/5;
    }
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        this.baseDamage = AbstractDungeon.player.gold/5;
    }
    public void applyPowers() {
        this.baseDamage = AbstractDungeon.player.gold/5;
        super.applyPowers();
        this.initializeDescription();
    }
    //// END GOLDEN CODE ////


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
