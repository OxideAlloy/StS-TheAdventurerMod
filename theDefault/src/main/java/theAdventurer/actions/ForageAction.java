package theAdventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.PetalEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import theAdventurer.powers.SatiatedPower_TA;

public class ForageAction extends AbstractGameAction {
    private int magicNumber;
    private AbstractPlayer p;

    public ForageAction(final int magicNumber) {
        this.actionType = ActionType.DRAW;
        this.target = AbstractDungeon.player;
        this.magicNumber = magicNumber;

    }

    public void update() {
        if (!this.target.hasPower("theAdventurer:SatiatedPower_TA") || this.target.hasPower("theAdventurer:BrownMaskPower_TA")) {
            this.addToTop(new DrawCardAction(this.target, magicNumber));
            this.addToBot(new ApplyPowerAction(this.target, this.target, new SatiatedPower_TA(this.target, this.magicNumber), this.magicNumber));
        }
        //System.out.println("Draw amount in ForageAction is:"+magicNumber);
        this.isDone = true;
    }

}