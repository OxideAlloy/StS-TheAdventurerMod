package theAdventurer.potions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import basemod.abstracts.CustomPotion;
import theAdventurer.TheAdventurerMod;

public class DeathPotion_P_TA extends CustomPotion {

    public static final String POTION_ID = TheAdventurerMod.makeID("DeathPotion_P_TA");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private static final int AMOUNT = 0;

    public DeathPotion_P_TA() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.GHOST, PotionColor.ENERGY);
        potency = getPotency();
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        isThrown = true;
        this.targetRequired = true;
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void use(AbstractCreature target) {
        //this.addToBot(new LoseHPAction(target, AbstractDungeon.player, this.potency, AbstractGameAction.AttackEffect.POISON));
        // target loses 20% or 40% of their current health
        this.addToBot(new LoseHPAction(target, AbstractDungeon.player, (target.currentHealth*this.potency/100), AbstractGameAction.AttackEffect.POISON));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new DeathPotion_P_TA();
    }

    // This is your potency.
    @Override
    public int getPotency(final int potency) {
        return 20;
    }

    public void upgradePotion()
    {
        potency += 20;
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
}



