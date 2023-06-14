package theAdventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

import java.util.Iterator;
import java.util.UUID;

public class HatchAction extends AbstractGameAction {
    private AbstractCard card;
    private UUID uuid;

    private boolean cardCreated;

  public HatchAction(UUID targetUUID) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
        //this.decreaseAmount = 1;
        this.uuid = targetUUID;
        cardCreated = false;
    }

    public void update() {
        Iterator var1 = GetAllInBattleInstances.get(this.uuid).iterator();
        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
                c.baseMagicNumber -= 1;
                if(c.magicNumber <= 0) {
                c.exhaust = true;
                this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
                this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                if(!cardCreated) {
                    this.addToBot(new MakeTempCardInDiscardAction(c.cardsToPreview, 1));

                    cardCreated = true;
                }
            }
        }
        //this.addToBot(new TalkAction(true, "@HatchAction Called@", 1.0F, 1.0F));
        this.isDone = true;
    }
}

