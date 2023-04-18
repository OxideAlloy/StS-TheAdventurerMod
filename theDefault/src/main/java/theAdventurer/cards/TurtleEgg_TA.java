package theAdventurer.cards;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theAdventurer.TheAdventurerMod;
import theAdventurer.actions.HatchAction;
import theAdventurer.characters.TheAdventurer;
import theAdventurer.util.CustomTags;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class TurtleEgg_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(TurtleEgg_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("TurtleEgg_TA.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int BLOCK = 10;
    private static final int UPGRADE_PLUS_BLOCK = 4;

    private static final int MAGIC = 4;
    //private static final int UPGRADE_PLUS_MAGIC = -1;

//    private static final int MAGICTWO = 4;
//    private static final int UPGRADE_PLUS_MAGICTWO = -1;

    private boolean discardCheck = false;

    public TurtleEgg_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = MAGIC;
        //See Beaked for Wither effect
        //Discarding an egg card should also trigger this countdown
        tags.add(CustomTags.EGG_CARD);
        this.cardsToPreview = new TurtleShield_TA();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, this.block), this.block));
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

//    @Override
//    public void teleportToDiscardPile() {
//        this.current_x = (float)CardGroup.DISCARD_PILE_X;
//        this.target_x = this.current_x;
//        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
//            this.current_y = 0.0F;
//        } else {
//            this.current_y = 0.0F - OverlayMenu.HAND_HIDE_Y;
//        }
//        this.target_y = this.current_y;
//        teleported = true;
//        this.onMoveToDiscard();
//        teleported = false;
//        this.addToBot(new TalkAction(true, "teleportToDiscardPile called", 1.0F, 1.0F));
//    }

//    @Override
//    public void onMoveToDiscard() {
//            this.addToBot(new TalkAction(true, "onMoveToDiscard CALLED", 2.0F, 2.0F));
//    }

//    public void teleportToDiscardPile() {
//        this.addToBot(new TalkAction(true, "teleportToDiscardPile", 1.0F, 1.0F));
//        this.current_x = (float) CardGroup.DISCARD_PILE_X;
//        this.target_x = this.current_x;
//        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
//            this.current_y = 0.0F;
//        } else {
//            this.current_y = 0.0F - OverlayMenu.HAND_HIDE_Y;
//        }
//
//        this.target_y = this.current_y;
//        this.onMoveToDiscard();
//    }



//    public void triggerWhenDrawn() {
//
//    }
//
//    @Override
//    public void triggerOnManualDiscard() {
//
//    }

//    public void teleportToDiscardPile() {
//        System.out.print("***** teleportToDiscardPile CALLED HERE, teleportCounter: " + teleportCounter);
//        //check if !AbstractDungeon.player.discardPile contains this
//        //this.dontTrigger = true;
//    }

//        LookingAtDiscard = true;

//        if(AbstractDungeon.player.discardPile==CardGroup.CardGroupType.DISCARD_PILE){
//            System.out.print("teleportToDiscardPile called while card is in Discard Pile");
//        }
//        if(AbstractDungeon.player.discardPile==CardGroup.CardGroupType){
//            System.out.print("teleportToDiscardPile called while card is in Discard Pile");
//        }


//    @Override
//    public void onMoveToDiscard() {
//        System.out.print("***** onMoveToDiscard CALLED HERE, moveCounter: "+moveCounter);
//
//        //is this triggering when you just look at the discard pile wtf???
////        if(LookingAtDiscard==false) {
////            if (this.defaultBaseSecondMagicNumber > 1) {
////
////                this.upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_MAGICTWO);
////            } else {
////                this.exhaust = true;
////                this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.discardPile));
////                this.addToBot(new MakeTempCardInDiscardAction(this.cardsToPreview, 1));
////            }
////        }
////
////        //
////        if(LookingAtDiscard==true){
////            LookingAtDiscard=false;
////        }
//    }




    @Override
    public void upgrade() {
        if (!upgraded) {
            this.cardsToPreview.upgrade();
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
//            upgradeBaseCost(UPGRADED_COST);
//            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            //this.upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_MAGICTWO);
            initializeDescription();
        }
    }
}
