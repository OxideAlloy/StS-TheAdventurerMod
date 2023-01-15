package theAdventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAdventurer.TheAdventurerMod;
import theAdventurer.powers.SautéPower_TA;

import java.util.Iterator;

public class MultiplySautéAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int multiplyAmount;

    public MultiplySautéAction(final AbstractPlayer p, final int multiplyAmount) {
        //this.target = target;
        this.p = p;
        this.actionType = ActionType.DEBUFF;
        this.attackEffect = AttackEffect.FIRE;
        this.multiplyAmount = multiplyAmount-1;
    }

    public void update() {
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = (AbstractMonster)var3.next();
            if (mo != null && mo.hasPower(SautéPower_TA.POWER_ID)) {
                this.addToBot(new ApplyPowerAction(mo, this.p, new SautéPower_TA(mo, this.p, mo.getPower(SautéPower_TA.POWER_ID).amount * multiplyAmount), mo.getPower(SautéPower_TA.POWER_ID).amount * multiplyAmount, AbstractGameAction.AttackEffect.FIRE));
            }
        }
        this.isDone = true;
    }
}
