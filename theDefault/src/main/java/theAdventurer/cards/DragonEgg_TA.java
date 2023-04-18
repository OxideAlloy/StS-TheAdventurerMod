package theAdventurer.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAdventurer.TheAdventurerMod;
import theAdventurer.actions.HatchAction;
import theAdventurer.characters.TheAdventurer;
import theAdventurer.powers.SautéPower_TA;
import theAdventurer.util.CustomTags;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class DragonEgg_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(DragonEgg_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("DragonEgg_TA.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 2;

    private static final int MAGIC = 5;
    private static final int UPGRADE_PLUS_MAGIC = -2;

    private static final int MAGICTWO = 10;

    private boolean discardCheck = false;

    public DragonEgg_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = MAGICTWO;
        tags.add(CustomTags.EGG_CARD);
        this.cardsToPreview = new DragonFire_TA();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new SautéPower_TA(m, p, this.defaultSecondMagicNumber), this.defaultSecondMagicNumber, AbstractGameAction.AttackEffect.FIRE));
        discardCheck = true;
    }

    ////////////////
    // Hatch code //
    ////////////////
    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
        discardCheck = true;
    }
    @Override
    public void triggerOnManualDiscard() {
        discardCheck = true;
    }
    @Override
    public void triggerOnScry() {
        discardCheck = true;
    }
    @Override
    public void onMoveToDiscard() {
        if(discardCheck == true) {
            this.addToBot(new HatchAction(this.uuid));
            discardCheck = false;
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.cardsToPreview.upgrade();
            upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            initializeDescription();
        }
    }
}
