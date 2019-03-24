package questionableDecisions.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import questionableDecisions.MORECHAOSMOREPOWER;
import questionableDecisions.characters.PUTMEOUTOFMYMISERY;

import java.util.Random;


public class NMustBePositiveXfuckingD {

        @SpirePatch(
                clz = CardGroup.class,
                method = "getRandomCard",
                paramtypez = {
                        Random.class
                }
        )
        public static class Meth {
                public static SpireReturn<AbstractCard> Prefix(CardGroup __instance, Random useless) {
                        if (AbstractDungeon.player instanceof PUTMEOUTOFMYMISERY) {
                                return SpireReturn.Return(MORECHAOSMOREPOWER.generateCard());
                        }
                        return SpireReturn.Continue();
                }
        }

        @SpirePatch(
                clz = CardGroup.class,
                method = "getRandomCard",
                paramtypez =  {
                        boolean.class
                }
        )
        public static class Coke {
                public static SpireReturn<AbstractCard> Prefix(CardGroup __instance, boolean useless) {
                        if (AbstractDungeon.player instanceof PUTMEOUTOFMYMISERY) {
                                return SpireReturn.Return(MORECHAOSMOREPOWER.generateCard());
                        }
                        return SpireReturn.Continue();
                }
        }

        @SpirePatch(
                clz = CardGroup.class,
                method = "getRandomCard",
                paramtypez =  {
                        boolean.class,
                        AbstractCard.CardRarity.class
                }
        )
        public static class Heroin {
                public static SpireReturn<AbstractCard> Prefix(CardGroup __instance, boolean useless, AbstractCard.CardRarity useless2) {
                        if (AbstractDungeon.player instanceof PUTMEOUTOFMYMISERY) {
                                return SpireReturn.Return(MORECHAOSMOREPOWER.generateCard());
                        }
                        return SpireReturn.Continue();
                }
        }

        @SpirePatch(
                clz = CardGroup.class,
                method = "getRandomCard",
                paramtypez =  {
                        AbstractCard.CardType.class,
                        boolean.class
                }
        )
        public static class Fetanyl  {
                public static SpireReturn<AbstractCard> Prefix(CardGroup __instance, AbstractCard.CardType useless, boolean useless2) {
                        if (AbstractDungeon.player instanceof PUTMEOUTOFMYMISERY) {
                                return SpireReturn.Return(MORECHAOSMOREPOWER.generateCard());
                        }
                        return SpireReturn.Continue();
                }
        }
}
