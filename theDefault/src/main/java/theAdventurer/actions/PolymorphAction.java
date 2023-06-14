package theAdventurer.actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.vfx.PetalEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

import java.util.Iterator;
import java.util.UUID;

public class PolymorphAction extends AbstractGameAction {
    private AbstractCard c;

//    public PolymorphAction(AbstractCard card) {
//        this.card = card;
//    }

    public PolymorphAction(AbstractCard c) {
        this.c = c;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        //this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.WAIT;
    }

    public void update() {

        this.addToTop(new DiscardTopDeckAction());

        Iterator var2 = AbstractDungeon.actionManager.actions.iterator();
        while (var2.hasNext()) {
            AbstractGameAction act = (AbstractGameAction) var2.next();
            if (act instanceof UseCardAction) {
                UseCardAction action = (UseCardAction) act;
                action.reboundCard = true;
            }
        }
        this.isDone = true;
    }
}










//        if (this.duration == Settings.ACTION_DUR_FAST) {
//            if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
//                this.isDone = true;
//                return;
//            }
//
////            if (AbstractDungeon.player.drawPile.isEmpty()) {
////                this.addToTop(new DiscardTopDeckAction());
////                this.addToTop(new EmptyDeckShuffleAction());
////                this.isDone = true;
////                return;
////            }
//
//            if (!AbstractDungeon.player.drawPile.isEmpty()) {
//                AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
//                AbstractDungeon.player.drawPile.group.remove(card);
//                AbstractDungeon.getCurrRoom().souls.remove(card);
//                //card.exhaustOnUseOnce = this.exhaustCards;
//                AbstractDungeon.player.limbo.group.add(card);
//                card.current_y = -200.0F * Settings.scale;
//                card.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
//                card.target_y = (float)Settings.HEIGHT / 2.0F;
//                card.targetAngle = 0.0F;
//                card.lighten(false);
//                card.drawScale = 0.12F;
//                card.targetDrawScale = 0.75F;
//                card.applyPowers();
//                //this.addToTop(new NewQueueCardAction(card, this.target, false, true));
//                this.addToTop(new UnlimboAction(card));
//                AbstractDungeon.player.discardPile.group.add(card);
//                if (!Settings.FAST_MODE) {
//                    this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
//                } else {
//                    this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
//                }
//            }
//
//            this.isDone = true;
//        }
//    }
//
//
//
//
//
//
//
//
//        AbstractCard c;
//
//        for (AbstractGameAction action : AbstractDungeon.actionManager.actions) {
//            if (action instanceof UseCardAction) {
//                //this.MillTop();
//                //((UseCardAction) action).reboundCard = true;
//
//
//                if (true) {
//                    AbstractDungeon.player.hand.moveToDeck(this.c , false);
//                } else if (this.targetCard.shuffleBackIntoDrawPile) {
//                    AbstractDungeon.player.hand.moveToDeck(this.targetCard, true);
//                } else if (this.targetCard.returnToHand) {
//                    AbstractDungeon.player.hand.moveToHand(this.targetCard);
//                    AbstractDungeon.player.onCardDrawOrDiscard();
//                } else {
//                    AbstractDungeon.player.hand.moveToDiscardPile(this.targetCard);
//                }
//
//
//
//                break;
//            }
//        }
//        this.isDone = true;
//    }
//
//    //See Vacant for similar code
//    private void MillTop() {
//        if (AbstractDungeon.player.drawPile.size() > 0) {
//            AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
//            if(card != null) {
//                AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(card));
//            }
//        }
//
//    }

