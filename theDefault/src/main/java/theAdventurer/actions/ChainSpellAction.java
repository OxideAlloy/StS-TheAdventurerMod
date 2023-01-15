package theAdventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theAdventurer.powers.SautéPower_TA;

import java.util.Iterator;

public class ChainSpellAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int powerAmount;

    public ChainSpellAction() {
        this.actionType = ActionType.DEBUFF;
        this.powerAmount = 0;
    }

    public void update() {
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = (AbstractMonster)var3.next();
            if (mo != null && mo.hasPower(MarkPower.POWER_ID)) {
                powerAmount = mo.getPower(MarkPower.POWER_ID).amount;
                this.addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, powerAmount, false), powerAmount));
            }
        }
        this.isDone = true;
    }

}