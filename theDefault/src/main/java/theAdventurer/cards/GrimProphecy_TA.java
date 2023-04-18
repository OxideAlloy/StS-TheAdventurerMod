package theAdventurer.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.ThirdEyeEffect;
import theAdventurer.TheAdventurerMod;
import theAdventurer.characters.TheAdventurer;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class GrimProphecy_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(GrimProphecy_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("GrimProphecy_TA.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = -2;


    public GrimProphecy_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

    }

    @Override
    public void triggerWhenDrawn() {
        if (AbstractDungeon.player != null) {
            this.addToBot(new VFXAction(new ThirdEyeEffect( AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY)));
        }
        AbstractDungeon.actionManager.addToBottom(new SFXAction("EVENT_WINDING"));

        this.addToBot(new ScryAction(13));
        //this.addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false), 1));
        if (!upgraded) {
            this.addToBot(new LoseEnergyAction(1));
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            rawDescription = UPGRADE_DESCRIPTION;
            upgradeName();
            initializeDescription();
        }
    }
}
