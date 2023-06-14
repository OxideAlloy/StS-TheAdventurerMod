package theAdventurer.cards;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAdventurer.TheAdventurerMod;
import theAdventurer.actions.BottleAction;
import theAdventurer.characters.TheAdventurer;
import theAdventurer.potions.MinorLavaPotion_P_TA;
import theAdventurer.util.CustomTags;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class LavaPotion_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(LavaPotion_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("LavaPotion_TA.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    public LavaPotion_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.cardsToPreview = new Bottle_TA();
        tags.add(CustomTags.POTION_CARD);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new BottleAction(this));
        this.addToBot(new ObtainPotionAction(new MinorLavaPotion_P_TA()));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
