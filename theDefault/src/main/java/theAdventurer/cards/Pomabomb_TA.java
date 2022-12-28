package theAdventurer.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAdventurer.TheAdventurerMod;
import theAdventurer.actions.ForageAction;
import theAdventurer.characters.TheAdventurer;
import theAdventurer.util.CustomTags;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class Pomabomb_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(Pomabomb_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("Pomabomb_TA.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int DAMAGE = 3;
    private static final int UPGRADE_PLUS_DMG = 1;

    private static final int MAGIC = 3;
    private static final int UPGRADE_PLUS_MAGIC = 2;

    public Pomabomb_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < 3; ++i) {
            this.addToBot(new SFXAction("ATTACK_FIRE"));
            this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
        }
        this.addToBot(new ForageAction(1));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
