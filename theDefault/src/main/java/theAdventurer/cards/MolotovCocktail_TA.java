package theAdventurer.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAdventurer.TheAdventurerMod;
import theAdventurer.actions.FireScrollAction;
import theAdventurer.actions.MolotovAction;
import theAdventurer.characters.TheAdventurer;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class MolotovCocktail_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(MolotovCocktail_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("MolotovCocktail_TA.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int MAGIC = 5;
    private static final int UPGRADE_PLUS_MAGIC = 3;

    public MolotovCocktail_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MolotovAction(m, p, this.magicNumber));
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
