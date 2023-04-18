package theAdventurer.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import theAdventurer.TheAdventurerMod;
import theAdventurer.actions.HatchAction;
import theAdventurer.characters.TheAdventurer;
import theAdventurer.util.CustomTags;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class GoldenEgg_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(GoldenEgg_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("GoldenEgg_TA.png"); //TODO: Check image

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int MAGIC = 3;

    private static final int MAGICTWO = 5;
    private static final int UPGRADE_PLUS_MAGICTWO = 1;

    private boolean discardCheck = false;

    public GoldenEgg_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = MAGICTWO;
        tags.add(CustomTags.EGG_CARD);
        this.cardsToPreview = new PineShield_TA();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //p.gainGold(this.defaultSecondMagicNumber);
        for(int i = 0; i < this.defaultSecondMagicNumber; ++i) {
            AbstractDungeon.effectList.add(new GainPennyEffect(p, p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, true));
        }
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
