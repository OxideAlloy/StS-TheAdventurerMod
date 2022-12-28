package theAdventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.PetalEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

public class PolymorphAction extends AbstractGameAction {
    //private AbstractCard targetCard;
    public boolean reboundCard;

    public PolymorphAction() {
        this.actionType = ActionType.CARD_MANIPULATION;

    }

    public void update() {
        for (AbstractGameAction action : AbstractDungeon.actionManager.actions) {
            if (action instanceof UseCardAction) {
                this.MillTop();
                ((UseCardAction) action).reboundCard = true;
                break;
            }
        }
        this.isDone = true;
    }

    //See Vacant for similar code
    private void MillTop() {
        if (AbstractDungeon.player.drawPile.size() > 0) {
            AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
            if(card != null) {
                AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(card));
            }
        }

    }

}