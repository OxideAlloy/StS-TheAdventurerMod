package theAdventurer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAdventurer.TheAdventurerMod;
import theAdventurer.actions.FireScrollAction;
import theAdventurer.actions.MolotovAction;
import theAdventurer.actions.SautéAllAction;
import theAdventurer.util.TextureLoader;

import static theAdventurer.util.CustomTags.POTION_CARD;
import static theAdventurer.util.CustomTags.FOOD_CARD;


public class IndigestionPower_TA extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = TheAdventurerMod.makeID("IndigestionPower_TA");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theAdventurerResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theAdventurerResources/images/powers/placeholder_power32.png");

    public IndigestionPower_TA(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        type = PowerType.BUFF;
        isTurnBased = false;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

//    @Override
//    public void onCardDraw(AbstractCard card) {
//        for (int i = 0; i < this.amount; i++) {
//            if (card.cost>0  && card.hasTag(POLYMORPH_CARD)) {
//                card.setCostForTurn(card.costForTurn - 1);
//            }
//        }
//    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(POTION_CARD)|| card.hasTag(FOOD_CARD)) {
            this.flash();
            this.addToBot(new SautéAllAction(this.owner, this.amount));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new IndigestionPower_TA(owner, amount);
    }
}
