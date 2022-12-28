package theAdventurer.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import theAdventurer.TheAdventurerMod;
import theAdventurer.characters.TheAdventurer;
import theAdventurer.util.CustomTags;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class TurtleShield_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(TurtleShield_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("TurtleShield_TA.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int BLOCK = 7;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final int MAGIC = 1;

    public TurtleShield_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        //tags.add(CustomTags.EGG_CARD);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, this.block), this.block));
        this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
//            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            initializeDescription();
        }
    }
}
