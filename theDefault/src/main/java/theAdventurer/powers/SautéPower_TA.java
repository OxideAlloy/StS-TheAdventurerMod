package theAdventurer.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theAdventurer.TheAdventurerMod;
import theAdventurer.util.TextureLoader;

import static com.badlogic.gdx.math.MathUtils.floor;


public class SautéPower_TA extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = TheAdventurerMod.makeID("SautéPower_TA");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = theAdventurer.util.TextureLoader.getTexture("theAdventurerResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theAdventurerResources/images/powers/placeholder_power32.png");

    public SautéPower_TA(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.source = source;
        type = PowerType.DEBUFF;
        isTurnBased = true;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.flashWithoutSound();
        this.playApplyPowerSfx();
        this.addToBot(new DamageAction(this.owner, new DamageInfo(this.source, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        //Reduced by 1/2 rounded up each turn.  this.amount-this.amount/2 is kinda dumb, probably an easier way to round up.
        this.addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, this.amount-this.amount/2));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new SatiatedPower_TA(owner, amount);
    }
}
