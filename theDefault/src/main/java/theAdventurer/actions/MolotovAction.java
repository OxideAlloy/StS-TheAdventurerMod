package theAdventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;
import theAdventurer.powers.SautéPower_TA;

import java.util.Iterator;

public class MolotovAction extends AbstractGameAction {
    private float startingDuration;
    boolean bottleDiscarded;

    public MolotovAction(AbstractCreature target, AbstractCreature source, int amount) {
        this.target = target;
        this.source = source;
        this.amount = amount;
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = Settings.ACTION_DUR_FAST;
        bottleDiscarded = false;
    }

    public void update() {
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            //System.out.println("Card name is: "+c.name+" and ID is: "+c.cardID);
            if (c.cardID.equals("theAdventurer:Bottle_TA")) {
                //System.out.println("AND IS A BOTTLE");
                this.addToTop(new DiscardSpecificCardAction(c, AbstractDungeon.player.hand));
                bottleDiscarded = true;
                this.addToBot(new ApplyPowerAction(this.target, this.source, new SautéPower_TA(this.target, this.source, this.amount), this.amount, AbstractGameAction.AttackEffect.FIRE));
                if (this.target != null && this.target.hb != null) {
                    this.addToBot(new VFXAction(new PotionBounceEffect(this.source.hb.cX, this.source.hb.cY, this.target.hb.cX, this.target.hb.cY), 0.4F));
                    //this.addToTop(new VFXAction(new FireballEffect(this.source.hb.cX, this.source.hb.cY, this.target.hb.cX, this.target.hb.cY)));
                }
            }
        }
        if(!bottleDiscarded) {
            this.addToBot(new TalkAction(true, "I can't make a Molotov without an empty bottle!", 1.8F, 1.8F));
        }
        this.isDone = true;
    }

}