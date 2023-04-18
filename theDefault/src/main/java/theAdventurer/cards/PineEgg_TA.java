package theAdventurer.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAdventurer.TheAdventurerMod;
import theAdventurer.actions.HatchAction;
import theAdventurer.characters.TheAdventurer;
import theAdventurer.util.CustomTags;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class PineEgg_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(PineEgg_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("PineEgg_TA.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 0;

    private static final int BLOCK = 3;
    private static final int UPGRADE_PLUS_BLOCK = 1;

    private static final int MAGIC = 3;

    private boolean discardCheck = false;

    public PineEgg_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        baseBlock = BLOCK;
        tags.add(CustomTags.EGG_CARD);
        this.cardsToPreview = new PineShield_TA();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, block));
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
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
