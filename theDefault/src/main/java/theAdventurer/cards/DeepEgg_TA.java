package theAdventurer.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAdventurer.TheAdventurerMod;
import theAdventurer.actions.HatchAction;
import theAdventurer.characters.TheAdventurer;
import theAdventurer.util.CustomTags;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class DeepEgg_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(DeepEgg_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("DeepEgg_TA.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int MAGIC = 5;

    private static final int MAGICTWO = 3;
    private static final int UPGRADE_PLUS_MAGICTWO = 3;

    private boolean discardCheck = false;

    public DeepEgg_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = MAGICTWO;
        tags.add(CustomTags.EGG_CARD);
        this.cardsToPreview = new FromTheDepths_TA();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ScryAction(this.defaultSecondMagicNumber));
        discardCheck = true;
    }

    ////////////////
    // Hatch code //
    ////////////////
    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
        discardCheck = true;
    }
    @Override
    public void triggerOnManualDiscard() {
        discardCheck = true;
    }
    @Override
    public void triggerOnScry() {
        discardCheck = true;
    }
    @Override
    public void onMoveToDiscard() {
        if(discardCheck == true) {
            this.addToBot(new HatchAction(this.uuid));
            discardCheck = false;
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.cardsToPreview.upgrade();
            upgradeName();
            this.upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_MAGICTWO);
            initializeDescription();
        }
    }
}
