package theAdventurer.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAdventurer.TheAdventurerMod;
import theAdventurer.characters.TheAdventurer;
import theAdventurer.potions.MinorBlueTonic;
import theAdventurer.util.CustomTags;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class BlueVial_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(BlueVial_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("BlueVial_TA.png");

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 1;
//    private static final int UPGRADED_COST = 0;
//    private static final int BLOCK = 5;
//    private static final int UPGRADE_PLUS_BLOCK = 3;
//
//    private static final int MAGIC = 2;
//    private static final int UPGRADE_PLUS_MAGIC = 3;
//
//    private static final int MAGICTWO = 4;
//    private static final int UPGRADE_PLUS_MAGICTWO = 5;

    public BlueVial_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        //baseBlock = BLOCK;
        this.exhaust = true;
        this.cardsToPreview = new Bottle_TA();
        tags.add(CustomTags.POTION_CARD);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ObtainPotionAction(new MinorBlueTonic()));

        //this should be an action, should take into account if the card is upgraded so the bottle will be upgraded
        this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview, 1, true, true, false));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
//            upgradeBlock(UPGRADE_PLUS_BLOCK);
//            upgradeBaseCost(UPGRADED_COST);
//            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
//            this.upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_MAGICTWO);
            initializeDescription();
        }
    }
}
