package theAdventurer.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import theAdventurer.TheAdventurerMod;
import theAdventurer.actions.FireScrollAction;
import theAdventurer.actions.ForageAction;
import theAdventurer.characters.TheAdventurer;
import theAdventurer.powers.SautéPower_TA;

import java.util.Iterator;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class FireScroll_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(FireScroll_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("MagicScroll_TA.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int MAGIC = 3;
    private static final int UPGRADE_PLUS_MAGIC = 2;

    int powerAmount = 0;

    public FireScroll_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new MarkPower(m, this.magicNumber), this.magicNumber));
        this.addToBot(new FireScrollAction());
        //this.addToBot(new SFXAction("THUNDERCLAP", 0.05F));

        //this needs to apply after the MarkPower is applied, action?

//        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//        AbstractMonster mo;
//        while(var3.hasNext()) {
//            mo = (AbstractMonster)var3.next();
//            if (mo != null && mo.hasPower(MarkPower.POWER_ID)) {
//                powerAmount = mo.getPower(MarkPower.POWER_ID).amount;
//                this.addToBot(new VFXAction(new FireballEffect(p.drawX, p.drawY, mo.drawX, mo.drawY), 0.05F));
//                this.addToBot(new ApplyPowerAction(mo, p, new SautéPower_TA(mo, p, powerAmount), powerAmount, AbstractGameAction.AttackEffect.FIRE));
//            }
//        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            initializeDescription();
        }
    }
}
