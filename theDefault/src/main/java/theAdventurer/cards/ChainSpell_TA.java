package theAdventurer.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theAdventurer.TheAdventurerMod;
import theAdventurer.actions.ChainSpellAction;
import theAdventurer.actions.FireScrollAction;
import theAdventurer.characters.TheAdventurer;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class ChainSpell_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(ChainSpell_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("Chains_TA.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int MAGIC = 2;

    public ChainSpell_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new MarkPower(m, this.magicNumber), this.magicNumber));
        this.addToBot(new ChainSpellAction());
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
