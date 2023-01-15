package theAdventurer.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import theAdventurer.TheAdventurerMod;
import theAdventurer.characters.TheAdventurer;
import theAdventurer.powers.SautéPower_TA;

import java.util.Iterator;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class DragonFire_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(DragonFire_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("DragonFire_TA.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 2;

    private static final int MAGIC = 20;
    private static final int UPGRADE_PLUS_MAGIC = 5;

    public DragonFire_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = (AbstractMonster)var3.next();
            if (!mo.isDeadOrEscaped()) {
                this.addToBot(new ApplyPowerAction(mo, p, new SautéPower_TA(mo, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.FIRE));
                this.addToBot(new VFXAction(p, new InflameEffect(mo), 0.5F));
                //this.addToBot(new VFXAction(new LightningEffect(mo.drawX, mo.drawY), 0.05F));
            }
        }


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
