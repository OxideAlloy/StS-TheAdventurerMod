package theAdventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theAdventurer.util.CustomTags;

import java.util.ArrayList;
import java.util.Iterator;

public class DiscardEggsAction extends AbstractGameAction {
    //private float startingDuration;

    public DiscardEggsAction() {
        this.target = AbstractDungeon.player;
        this.actionType = ActionType.WAIT;
        //this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = Settings.ACTION_DUR_FAST;

    }

    public void update() {
        //int count = 0;

        ArrayList<AbstractCard> cardsToDiscard = new ArrayList();
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();

        AbstractCard c;
        while (var2.hasNext()) {
            c = (AbstractCard) var2.next();
            if (c.hasTag(CustomTags.EGG_CARD)) {
                cardsToDiscard.add(c);
                //count++;
            }
        }

//        var2 = cardsToDiscard.iterator();
//
//        while (var2.hasNext()) {
//            c = (AbstractCard) var2.next();
//            //this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.blockPerCard));
//        }

        var2 = cardsToDiscard.iterator();

        while (var2.hasNext()) {
            c = (AbstractCard) var2.next();

            this.addToTop(new DiscardSpecificCardAction(c, AbstractDungeon.player.hand));
            this.addToTop(new DrawCardAction(this.target, 1));
        }

        this.isDone = true;
    }
}