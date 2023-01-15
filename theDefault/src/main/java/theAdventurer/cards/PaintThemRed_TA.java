package theAdventurer.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAdventurer.TheAdventurerMod;
import theAdventurer.characters.TheAdventurer;
import theAdventurer.powers.GoredPower_TA;
import theAdventurer.powers.SautéPower_TA;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class PaintThemRed_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(PaintThemRed_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("PaintThemRed_TA.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int MAGIC = 1;
    private static final int UPGRADE_PLUS_MAGIC = 1;

    public PaintThemRed_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
        this.addToBot(new ApplyPowerAction(m, p, new GoredPower_TA(m, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.SMASH));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            initializeDescription();
        }
    }
}
