package questionableDecisions.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import questionableDecisions.characters.PUTMEOUTOFMYMISERY;

@SpirePatch(
        clz = UseCardAction.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez = {
                AbstractCard.class,
                AbstractCreature.class
        }
)
public class PlayerOnUseCard {
    public static void Postfix(UseCardAction __instance, AbstractCard c, AbstractCreature m) {
        if (AbstractDungeon.player instanceof PUTMEOUTOFMYMISERY) {
            ((PUTMEOUTOFMYMISERY)AbstractDungeon.player).onCardPlay();
        }
    }
}
