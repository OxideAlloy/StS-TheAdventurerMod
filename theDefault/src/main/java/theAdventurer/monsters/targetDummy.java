package theAdventurer.monsters;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateHopAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;


public class targetDummy extends AbstractMonster {
    public static final String ID = "Target_Dummy";
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final int HP_MIN = 20;
    private static final int HP_MAX = 24;

    private static final int BLOCK_AMT = 8;
    private static final int HEAL_AMT = 4;
    private static final byte CLAWS_DAMAGE = 3;
    private static final byte BITE_DAMAGE = 5;



    //Monster for testing
    public targetDummy(float x, float y) {
        super(NAME, "targetDummy", HP_MAX, 0.0F, 0.0F, 200.0F, 150.0F, (String)null, x, y);
//      (String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY) {
//      this(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, offsetX, offsetY, false);

        this.type = EnemyType.NORMAL ;

        this.setHp(HP_MIN, HP_MAX);

        //array 0
        this.damage.add(new DamageInfo(this, BITE_DAMAGE));
        //array 1
        this.damage.add(new DamageInfo(this, CLAWS_DAMAGE));

        this.loadAnimation("theAdventurerResources/images/monsters/targetDummy/skeleton.atlas", "theAdventurerResources/images/monsters/targetDummy/skeleton.json", 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation0", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        //this.state.addListener(new SlimeAnimListener());
    }

    public void takeTurn() {
        Iterator var1;
        AbstractMonster m;
        switch(this.nextMove) {
            //Apply 2 Weak and Claw Attack (3 dmg)
            case 1:
                AbstractDungeon.actionManager.addToBottom(new AnimateHopAction(this));
                AbstractDungeon.actionManager.addToBottom(new SFXAction("MONSTER_SLIME_ATTACK"));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 2, true), 2));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));;
                //Show intent for next move
                this.setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo)this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_VERTICAL));;
                //Show intent for next move
                this.setMove((byte)1, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base);
        }
    }

    public void damage(DamageInfo info) {
        super.damage(info);
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
            this.state.setAnimation(0, "animation1", false);
            this.state.addAnimation(0, "animation0", true, 0.0F);
        }
    }

    protected void getMove(int num) {
//        //18 - Elites have more challenging movesets and abilities. (NOT IMPLEMENTED)

        //Set starting intent
        this.setMove((byte)1, Intent.ATTACK_DEBUFF);
    }

    static {
        //NEED TO ADD/UPDATE STRINGS
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("theAdventurer:targetDummy");
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;
    }
}
