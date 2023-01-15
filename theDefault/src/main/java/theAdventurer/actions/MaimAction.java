package theAdventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import theAdventurer.powers.GoredPower_TA;

public class MaimAction extends AbstractGameAction {
    //public int[] multiDamage;
    private boolean freeToPlayOnce;
    private int damage;
    private DamageInfo.DamageType damageTypeForTurn;
    private AbstractPlayer p;
    private AbstractMonster m;
    private int energyOnUse = -1;
    private boolean upgraded;
    private int magicNumber;
    private int defaultBaseSecondMagicNumber;

    public MaimAction(AbstractPlayer p, AbstractMonster m, int damage, int magicNumber, DamageInfo.DamageType damageTypeForTurn, boolean freeToPlayOnce, boolean upgraded, int energyOnUse) {
        //this.multiDamage = multiDamage;
        this.damageTypeForTurn = damageTypeForTurn;
        this.p = p;
        this.m = m;
        this.damage = damage;
        //this.amount = amount;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.upgraded = upgraded;
        this.magicNumber = magicNumber;
        //this.defaultBaseSecondMagicNumber = defaultBaseSecondMagicNumber;

    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (this.upgraded) {
            ++effect;
        }

        if (effect > 0) {
            for(int i = 0; i < effect; ++i) {
//                if (i == 0) {
//                    this.addToBot(new SFXAction("ATTACK_WHIRLWIND"));
//                    this.addToBot(new VFXAction(new WhirlwindEffect(), 0.0F));
//                }
                //this.addToBot(new SFXAction("ATTACK_HEAVY"));
                //this.addToBot(new VFXAction(this.p, new CleaveEffect(), 0.0F));
                //this.addToBot(new DamageAllEnemiesAction(this.p, this.multiDamage, this.damageType, AttackEffect.NONE, true));
                //this.addToBot(new DamageAction(this.target, new DamageInfo(this.p, damage, this.damageType), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                this.addToBot(new DamageAction(this.m, new DamageInfo(this.p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_LIGHT));
                this.addToBot(new ApplyPowerAction(m, p, new GoredPower_TA(m, p, this.magicNumber), this.magicNumber));
                this.addToBot(new ApplyPowerAction(this.m, this.p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }

}