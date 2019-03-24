package questionableDecisions.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.Defragment;
import com.megacrit.cardcrawl.cards.blue.StaticDischarge;
import com.megacrit.cardcrawl.daily.mods.ColorlessCards;
import com.megacrit.cardcrawl.daily.mods.Diverse;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.shop.ShopScreen;
import questionableDecisions.MORECHAOSMOREPOWER;
import questionableDecisions.characters.PUTMEOUTOFMYMISERY;

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

        @SpirePatch(
                clz = AbstractDungeon.class,
                method = "initializeCardPools"
        )
        public static class Heroin {
                public static SpireReturn Prefix(AbstractDungeon __instance) {
                        if (AbstractDungeon.player instanceof PUTMEOUTOFMYMISERY) {
                                AbstractDungeon.commonCardPool.clear();
                                AbstractDungeon.uncommonCardPool.clear();
                                AbstractDungeon.rareCardPool.clear();
                                AbstractDungeon.colorlessCardPool.clear();
                                AbstractDungeon.curseCardPool.clear();
                                ArrayList<AbstractCard> tmpPool = new ArrayList<>();
                                if (ModHelper.isModEnabled(ColorlessCards.ID)) {
                                        CardLibrary.addColorlessCards(tmpPool);
                                }

                                if (ModHelper.isModEnabled(Diverse.ID)) {
                                        CardLibrary.addRedCards(tmpPool);
                                        CardLibrary.addGreenCards(tmpPool);
                                        CardLibrary.addBlueCards(tmpPool);
                                }
                                for (int i = 0; i < 100; i++) {
                                        AbstractCard c = MORECHAOSMOREPOWER.generateCard();
                                        c.rarity = AbstractCard.CardRarity.COMMON;
                                        tmpPool.add(c);
                                }
                                for (int i = 0; i < 100; i++) {
                                        AbstractCard c = MORECHAOSMOREPOWER.generateCard();
                                        c.rarity = AbstractCard.CardRarity.UNCOMMON;
                                        tmpPool.add(c);
                                }
                                for (int i = 0; i < 100; i++) {
                                        AbstractCard c = MORECHAOSMOREPOWER.generateCard();
                                        c.rarity = AbstractCard.CardRarity.RARE;
                                        tmpPool.add(c);
                                }
                                for (AbstractCard c : tmpPool) {
                                        switch (c.rarity) {
                                                case CURSE:
                                                        AbstractDungeon.curseCardPool.addToTop(c);
                                                case COMMON:
                                                        AbstractDungeon.commonCardPool.addToTop(c);
                                                case UNCOMMON:
                                                        AbstractDungeon.uncommonCardPool.addToTop(c);
                                                case RARE:
                                                        AbstractDungeon.rareCardPool.addToTop(c);
                                        }

                                }
                                
                                AbstractDungeon.srcColorlessCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
                                AbstractDungeon.srcCurseCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
                                AbstractDungeon.srcRareCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
                                AbstractDungeon.srcUncommonCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
                                AbstractDungeon.srcCommonCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
                                
                                for (AbstractCard c : AbstractDungeon.curseCardPool.group) {
                                        srcCurseCardPool.addToTop(c);
                                }
                                for (AbstractCard c : AbstractDungeon.commonCardPool.group) {
                                        srcCommonCardPool.addToTop(c);
                                }
                                for (AbstractCard c : AbstractDungeon.uncommonCardPool.group) {
                                        srcUncommonCardPool.addToTop(c);
                                }
                                for (AbstractCard c : AbstractDungeon.rareCardPool.group) {
                                        srcRareCardPool.addToTop(c);
                                }
                                for (AbstractCard c : AbstractDungeon.colorlessCardPool.group) {
                                        srcColorlessCardPool.addToTop(c);
                                }

                                srcUncommonCardPool.addToTop(new StaticDischarge());
                                srcUncommonCardPool.addToTop(new Defragment());

                                return SpireReturn.Return(null);
                        }
                        return SpireReturn.Continue();
                }
        }
}
