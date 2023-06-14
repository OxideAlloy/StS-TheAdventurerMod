package theAdventurer.potions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import basemod.abstracts.CustomPotion;
import theAdventurer.TheAdventurerMod;
import theAdventurer.powers.SautéPower_TA;

public class MinorLavaPotion_P_TA extends CustomPotion {

    public static final String POTION_ID = TheAdventurerMod.makeID("MinorLavaPotion_P_TA");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public MinorLavaPotion_P_TA() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.BOTTLE, PotionColor.FIRE);
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        isThrown = true;
        this.targetRequired = true;
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void use(AbstractCreature target) {
        this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new SautéPower_TA(target, AbstractDungeon.player, this.potency), this.potency, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new DeathPotion_P_TA();
    }

    // This is your potency.
    @Override
    public int getPotency(final int potency) {
        return 10;
    }

    public void upgradePotion()
    {
        potency += 10;
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
}



