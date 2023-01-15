package theAdventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import java.util.Iterator;

public class BiteAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int damage;
    private DamageInfo.DamageType damageTypeForTurn;

    public BiteAction(AbstractPlayer p, int damage, DamageInfo.DamageType damageTypeForTurn) {
        this.actionType = ActionType.DAMAGE;
        this.p = p;
        this.damage = damage;
        this.damageTypeForTurn = damageTypeForTurn;
    }

    public void update() {
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = (AbstractMonster)var3.next();
            if (mo != null && mo.hasPower(MarkPower.POWER_ID)) {
                if (Settings.FAST_MODE) {
                    this.addToBot(new VFXAction(new BiteEffect(mo.hb.cX, mo.hb.cY - 40.0F * Settings.scale, Settings.RED_RELIC_COLOR.cpy()), 0.1F));
                } else {
                    this.addToBot(new VFXAction(new BiteEffect(mo.hb.cX, mo.hb.cY - 40.0F * Settings.scale, Settings.RED_RELIC_COLOR.cpy()), 0.3F));
                }
                //this.addToBot(new VFXAction(new BiteEffect(mo.hb.cX, mo.hb.cY - 40.0F * Settings.scale, Settings.RED_RELIC_COLOR.cpy()), 0.1F));
                this.addToBot(new DamageAction(mo, new DamageInfo(p, damage, damageTypeForTurn)));
            }
        }
        this.isDone = true;
    }

}