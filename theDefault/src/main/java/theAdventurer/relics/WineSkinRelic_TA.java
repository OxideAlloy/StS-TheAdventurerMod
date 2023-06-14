package theAdventurer.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theAdventurer.TheAdventurerMod;
import theAdventurer.util.TextureLoader;

import static theAdventurer.TheAdventurerMod.makeRelicOutlinePath;
import static theAdventurer.TheAdventurerMod.makeRelicPath;

public class WineSkinRelic_TA extends CustomRelic {

    public static final String ID = TheAdventurerMod.makeID("WineSkinRelic_TA");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public WineSkinRelic_TA() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.CLINK);
    }

    //Start with 1 extra potion slot
    public void onEquip() {
        AbstractPlayer var10000 = AbstractDungeon.player;
        var10000.potionSlots += 1;
        AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 1));
    }

    public AbstractRelic makeCopy() {
        return new WineSkinRelic_TA();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}