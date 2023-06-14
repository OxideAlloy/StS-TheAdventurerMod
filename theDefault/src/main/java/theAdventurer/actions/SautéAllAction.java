package theAdventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;
import theAdventurer.powers.SautéPower_TA;

import java.util.Iterator;

public class SautéAllAction extends AbstractGameAction {
    private float startingDuration;
    boolean bottleDiscarded;

    public SautéAllAction(AbstractCreature source, int amount) {
        //this.target = target;
        this.source = source;
        this.amount = amount;
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = Settings.ACTION_DUR_FAST;
        bottleDiscarded = false;
    }

    public void update() {
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = (AbstractMonster)var3.next();
//            if (Settings.FAST_MODE) {
//                this.addToBot(new VFXAction(new BiteEffect(mo.hb.cX, mo.hb.cY - 40.0F * Settings.scale, Settings.RED_RELIC_COLOR.cpy()), 0.1F));
//            } else {
//                this.addToBot(new VFXAction(new BiteEffect(mo.hb.cX, mo.hb.cY - 40.0F * Settings.scale, Settings.RED_RELIC_COLOR.cpy()), 0.3F));
//            }
            this.addToBot(new ApplyPowerAction(mo, this.source, new SautéPower_TA(mo, this.source, this.amount), this.amount, AbstractGameAction.AttackEffect.FIRE));
        }
        this.isDone = true;
    }

}