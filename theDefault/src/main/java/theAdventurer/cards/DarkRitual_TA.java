package theAdventurer.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DemonFormPower;
import com.megacrit.cardcrawl.powers.RitualPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theAdventurer.TheAdventurerMod;
import theAdventurer.characters.TheAdventurer;

import static theAdventurer.TheAdventurerMod.makeCardPath;

public class DarkRitual_TA extends AbstractDynamicCard {

    public static final String ID = TheAdventurerMod.makeID(DarkRitual_TA.class.getSimpleName());
    public static final String IMG = makeCardPath("Ritual_TA.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAdventurer.Enums.COLOR_GRAY;

    private static final int COST = 1;

    public DarkRitual_TA() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        //this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower("theAdventurer:SatiatedPower_TA")) {
            this.addToBot(new ApplyPowerAction(p, p, new RitualPower(p, 1, true), 1));
            //this.addToBot(new ApplyPowerAction(p, p, new DemonFormPower(p, this.magicNumber), this.magicNumber));
            this.exhaust = true;
        } else {
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
            if (!this.upgraded) {
                this.exhaust = true;
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
