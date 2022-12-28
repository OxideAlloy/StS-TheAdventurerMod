package theAdventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Expunger;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;
import theAdventurer.cards.Bottle_TA;

public class BottleAction extends AbstractGameAction {
    //private AbstractPlayer p;
    //private AbstractCard originCard;
    //private boolean upgraded;
    private AbstractCard card;


    public BottleAction(AbstractCard card) {
        this.actionType = ActionType.SPECIAL ;
        this.card = card;
    }

//Currently Broken
    public void update() {
        //Should this upgrade the bottle if the potion card is upgraded?
        Bottle_TA c = new Bottle_TA();
        if (this.card.upgraded) {
            c.upgrade();
        }
        //c.upgrade();
        this.addToBot(new MakeTempCardInDrawPileAction(c, 1, true, true));
        //this.addToBot(new MakeTempCardInDrawPileAction(c, 1, true, true, false));
        //this.addToBot(new VFXAction(new PotionBounceEffect(p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY), 0.4F));
        this.isDone = true;
    }

}