package theAdventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class DiscardTopDeckAction extends AbstractGameAction {
    //private int magicNumber;
    //private AbstractPlayer p;
    //private int discardAmount;

    public DiscardTopDeckAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        //this.discardAmount = amount;

    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
                this.isDone = true;
                return;
            }

//            if (AbstractDungeon.player.drawPile.isEmpty()) {
//                this.addToTop(new DiscardTopDeckAction());
//                this.addToTop(new EmptyDeckShuffleAction());
//                this.isDone = true;
//                return;
//            }

            if (!AbstractDungeon.player.drawPile.isEmpty()) {
                AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
                AbstractDungeon.player.drawPile.group.remove(card);
                AbstractDungeon.getCurrRoom().souls.remove(card);
                //card.exhaustOnUseOnce = this.exhaustCards;
                AbstractDungeon.player.limbo.group.add(card);
                card.current_y = -200.0F * Settings.scale;
                card.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
                card.target_y = (float)Settings.HEIGHT / 2.0F;
                card.targetAngle = 0.0F;
                card.lighten(false);
                card.drawScale = 0.12F;
                card.targetDrawScale = 0.75F;
                card.applyPowers();
                //this.addToTop(new NewQueueCardAction(card, this.target, false, true));
                this.addToTop(new UnlimboAction(card));
                AbstractDungeon.player.discardPile.group.add(card);
                if (!Settings.FAST_MODE) {
                    this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
                } else {
                    this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
                }
            }

            this.isDone = true;
        }
    }

}