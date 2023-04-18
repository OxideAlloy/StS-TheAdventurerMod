package theAdventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theAdventurer.cards.Bottle_TA;
import theAdventurer.util.CustomTags;

import java.util.ArrayList;
import java.util.Iterator;

import static theAdventurer.util.CustomTags.POTION_CARD;

public class PotionExhumeAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCard theCard = null;

    public PotionExhumeAction() {
        this.p = AbstractDungeon.player;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        //this.card = card;
    }

    public void update() {
        ArrayList<AbstractCard> possibleCards = new ArrayList();
        Iterator var2 = AbstractDungeon.player.exhaustPile.group.iterator();
        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.hasTag(POTION_CARD)) {
                possibleCards.add(c);
            }
        }
        if (!possibleCards.isEmpty()) {
            this.theCard = (AbstractCard)possibleCards.get(AbstractDungeon.miscRng.random(0, possibleCards.size() - 1));
            this.p.hand.addToHand(this.theCard);
            this.p.exhaustPile.removeCard(this.theCard);
            this.theCard.unhover();
            this.theCard.fadingOut = false;
            this.p.hand.refreshHandLayout();
        }
        this.isDone = true;

    }

}