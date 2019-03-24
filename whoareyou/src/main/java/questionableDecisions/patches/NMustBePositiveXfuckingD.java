package questionableDecisions.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.Defragment;
import com.megacrit.cardcrawl.cards.blue.StaticDischarge;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.daily.mods.ColorlessCards;
import com.megacrit.cardcrawl.daily.mods.Diverse;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.shop.Merchant;
import com.megacrit.cardcrawl.shop.ShopScreen;
import questionableDecisions.MORECHAOSMOREPOWER;
import questionableDecisions.characters.PUTMEOUTOFMYMISERY;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;


public class NMustBePositiveXfuckingD {

        @SpirePatch(
                clz = AbstractDungeon.class,
                method = "getRewardCards"

        )
        public static class Ecstacy {
                @SpireInsertPatch(
                        rloc = 17,
                        localvars = {
                                "numCards"
                        }
                )
                public static SpireReturn<ArrayList<AbstractCard>> PCP(int numCards) {
                        if (AbstractDungeon.player instanceof PUTMEOUTOFMYMISERY) {
                                ArrayList<AbstractCard> dab = new ArrayList<>();
                                for (int i = 0; i < numCards; i++) {
                                        dab.add(MORECHAOSMOREPOWER.generateCard());
                                }
                                return SpireReturn.Return(dab);
                        }
                        return SpireReturn.Continue();
                }
        }

}
