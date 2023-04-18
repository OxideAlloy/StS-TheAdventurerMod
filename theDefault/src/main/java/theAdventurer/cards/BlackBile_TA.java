package theAdventurer.cards;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAdventurer.TheAdventurerMod;
import theAdventurer.characters.TheAdventurer;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class BlackBile_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(BlackBile_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("BlackBile_TA.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 1;

    public BlackBile_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower("theAdventurer:SatiatedPower_TA")) {
            this.addToBot(new TalkAction(true, "@BlEUURGH@", 1.5F, 1.5F));
            this.addToTop(new RemoveSpecificPowerAction(p, p, "theAdventurer:SatiatedPower_TA"));
            this.exhaust = true;
        }
    }

    @Override
    public void upgrade() {
//        if (!upgraded) {
//            upgradeName();
//            initializeDescription();
//        }
    }
}
