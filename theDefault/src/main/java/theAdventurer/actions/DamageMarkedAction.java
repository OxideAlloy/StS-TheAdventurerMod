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
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import theAdventurer.powers.SautéPower_TA;

import java.util.Iterator;

public class DamageMarkedAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int damage;
    private int powerAmount;
    private DamageInfo.DamageType damageTypeForTurn;

    public DamageMarkedAction(AbstractPlayer p, DamageInfo.DamageType damageTypeForTurn) {
        this.actionType = ActionType.DAMAGE;
        this.p = p;
        //this.damage = damage;
        this.damageTypeForTurn = damageTypeForTurn;
    }

    public void update() {
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = (AbstractMonster)var3.next();
            if (mo != null && mo.hasPower(MarkPower.POWER_ID)) {
                if (!Settings.FAST_MODE) {
                    //this.addToBot(new VFXAction(new LightningEffect(mo.hb.cX, mo.hb.cY - 40.0F * Settings.scale), 0.1F));
                    this.addToBot(new VFXAction(new FireballEffect(AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, mo.drawX, mo.drawY), 0.05F));
                }
                powerAmount = mo.getPower(MarkPower.POWER_ID).amount;
                this.addToBot(new DamageAction(mo, new DamageInfo(p, powerAmount, damageTypeForTurn)));
            }
        }
        this.isDone = true;
    }

}