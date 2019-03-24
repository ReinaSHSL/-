package questionableDecisions.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.shop.ShopScreen;
import questionableDecisions.MORECHAOSMOREPOWER;
import questionableDecisions.characters.PUTMEOUTOFMYMISERY;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.returnRandomCurse;


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

        @SpirePatch(
                clz = AbstractDungeon.class,
                method = "getCard",
                paramtypez = {
                        AbstractCard.CardRarity.class
                }
        )
        public static class LSD {
                public static SpireReturn<AbstractCard> Prefix(AbstractCard.CardRarity useless) {
                        if (AbstractDungeon.player instanceof PUTMEOUTOFMYMISERY) {
                                if (useless == AbstractCard.CardRarity.CURSE) {
                                        return SpireReturn.Return(AbstractDungeon.curseCardPool.getRandomCard(true));
                                }
                                AbstractCard c = MORECHAOSMOREPOWER.generateCard();
                                return SpireReturn.Return(c);
                        }
                        return SpireReturn.Continue();
                }
        }

        @SpirePatch(
                clz = AbstractDungeon.class,
                method = "getCard",
                paramtypez = {
                        AbstractCard.CardRarity.class,
                        Random.class
                }
        )
        public static class BathSalts {
                public static SpireReturn<AbstractCard> Prefix(AbstractCard.CardRarity useless, Random useless2) {
                        if (AbstractDungeon.player instanceof PUTMEOUTOFMYMISERY) {
                                if (useless == AbstractCard.CardRarity.CURSE) {
                                        return SpireReturn.Return(AbstractDungeon.curseCardPool.getRandomCard(true));
                                }
                                AbstractCard c = MORECHAOSMOREPOWER.generateCard();
                                return SpireReturn.Return(c);
                        }
                        return SpireReturn.Continue();
                }
        }

        @SpirePatch(
                clz = AbstractDungeon.class,
                method = "getCardWithoutRng",
                paramtypez = {
                        AbstractCard.CardRarity.class,
                }
        )
        public static class Benzos {
                public static SpireReturn<AbstractCard> Prefix(AbstractCard.CardRarity useless) {
                        if (AbstractDungeon.player instanceof PUTMEOUTOFMYMISERY) {
                                if (useless == AbstractCard.CardRarity.CURSE) {
                                        return SpireReturn.Return(returnRandomCurse());
                                }
                                AbstractCard c = MORECHAOSMOREPOWER.generateCard();
                                return SpireReturn.Return(c);
                        }
                        return SpireReturn.Continue();
                }
        }

        @SpirePatch(
                clz = AbstractDungeon.class,
                method = "getCardFromPool"
        )
        public static class Meth {
                public static SpireReturn<AbstractCard> Prefix(AbstractCard.CardRarity rarity, AbstractCard.CardType type, boolean useRng) {
                        if (AbstractDungeon.player instanceof PUTMEOUTOFMYMISERY) {
                                if (rarity == AbstractCard.CardRarity.CURSE) {
                                        return SpireReturn.Return(returnRandomCurse());
                                }
                                AbstractCard c = MORECHAOSMOREPOWER.generateCard();
                                return SpireReturn.Return(c);
                        }
                        return SpireReturn.Continue();
                }
        }
}
