package theAdventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.PetalEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import theAdventurer.powers.SatiatedPower_TA;

import java.util.Iterator;
import java.util.UUID;

public class HatchAction extends AbstractGameAction {
    private AbstractCard card;

    private int decreaseAmount;
    private UUID uuid;

  public HatchAction(UUID targetUUID) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.decreaseAmount = -1;
        this.uuid = targetUUID;
    }

    public void update() {
        Iterator var1 = GetAllInBattleInstances.get(this.uuid).iterator();
        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();

            if (c.magicNumber > 1) {
                c.baseMagicNumber += this.decreaseAmount;
            } else {
                c.exhaust = true;
                this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
                this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                this.addToBot(new MakeTempCardInDiscardAction(c.cardsToPreview, 1));
            }
        }
        //this.addToBot(new TalkAction(true, "@HatchAction Called@", 1.0F, 1.0F));
        this.isDone = true;
    }
}

