package theAdventurer.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theAdventurer.TheAdventurerMod;
import theAdventurer.actions.ForageAction;
import theAdventurer.actions.PolymorphAction;
import theAdventurer.characters.TheAdventurer;
import theAdventurer.powers.StoutPower_TA;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class WildMushroom_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(WildMushroom_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("WildMushroom_TA.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 1;
    //private static final int UPGRADED_COST = 0;

    private static final int MAGIC = 2;
    //private static final int UPGRADE_PLUS_MAGIC = 1;

    private static final int MAGICTWO = 1;
    private static final int UPGRADE_PLUS_MAGICTWO = 1;

    public WildMushroom_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = MAGICTWO;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ForageAction(this.magicNumber));
        if (AbstractDungeon.player.hasPower("theAdventurer:SatiatedPower_TA")) {
            this.addToBot(new ApplyPowerAction(p, p, new StoutPower_TA(p, this.defaultBaseSecondMagicNumber), this.defaultBaseSecondMagicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_MAGICTWO);
            initializeDescription();
        }
    }
}
