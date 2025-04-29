package dev.dassen.desolatedungeons.screen.custom;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class LevelUpScreen extends Screen {
    public static final Identifier GUI_TEXTURE = Identifier.of("desolate_dungeons:textures/gui/level_up.png");
    public static final Identifier BUTTON_DISABLED_TEXTURE = Identifier.of("desolate_dungeons:container/level_up/button");
    public static final Identifier BUTTON_HIGHLIGHTED_TEXTURE = Identifier.of("desolate_dungeons:container/level_up/button");
    public static final Identifier BUTTON_TEXTURE = Identifier.of("desolate_dungeons:container/level_up/button");
    private static final int BACKGROUND_WIDTH = 182;
    private static final int BACKGROUND_HEIGHT = 88;
    private static final int BUTTON_SIZE = 20;

    public LevelUpScreen() {
        super(Text.literal("Level Up"));
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        renderInGameBackground(context);
        context.drawTexture(
            RenderLayer::getGuiTextured,
            GUI_TEXTURE,
            (this.width - BACKGROUND_WIDTH) / 2,
            (this.height - BACKGROUND_HEIGHT) / 2,
            0f,
            0f,
            182,
            88,
            256,
            256
        );
    }

    @Override
    protected void init() {
        addAugmentButtons();
    }

    protected void addAugmentButtons() {
        int x = (this.width - BACKGROUND_WIDTH) / 2;
        int y = ((this.height - BACKGROUND_HEIGHT) / 2);

        addButton(new AugmentButtonWidget(x + 42, y + 44, new ItemStack(Items.PUFFERFISH)));
        addButton(new AugmentButtonWidget(x + 91, y + 44, new ItemStack(Items.PUFFERFISH)));
        addButton(new AugmentButtonWidget(x + 140, y + 44, new ItemStack(Items.PUFFERFISH)));
    }

    private <T extends ClickableWidget> void addButton(T button) {
        addDrawableChild(button);
    }

    abstract static class BaseButtonWidget extends PressableWidget {
        protected BaseButtonWidget(int x, int y) {
            super(x, y, BUTTON_SIZE, BUTTON_SIZE, ScreenTexts.EMPTY);
        }

        protected BaseButtonWidget(int x, int y, Text message) {
            super(x, y, BUTTON_SIZE, BUTTON_SIZE, message);
        }

        @Override
        public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
            Identifier identifier;
            if (!this.active) {
                identifier = BUTTON_DISABLED_TEXTURE;
            } else if (this.isSelected()) {
                identifier = BUTTON_HIGHLIGHTED_TEXTURE;
            } else {
                identifier = BUTTON_TEXTURE;
            }

            context.drawGuiTexture(RenderLayer::getGuiTextured, identifier, this.getX(), this.getY(), this.width, this.height);
            this.renderExtra(context);
        }

        protected abstract void renderExtra(DrawContext context);

        @Override
        public void appendClickableNarrations(NarrationMessageBuilder builder) {
            this.appendDefaultNarrations(builder);
        }
    }

    class AugmentButtonWidget extends BaseButtonWidget {
        int xCenter;
        int yCenter;
        ItemStack augmentItem;

        public AugmentButtonWidget(int xCenter, int yCenter, ItemStack itemStack) {
            super((xCenter - (BUTTON_SIZE / 2)), (yCenter - (BUTTON_SIZE / 2)));
            this.xCenter = xCenter;
            this.yCenter = yCenter;
            augmentItem = itemStack;
            init();
        }

        protected void init() {
            setTooltip(Tooltip.of(this.getAugmentName(), null));
        }

        protected MutableText getAugmentName() {
            return Text.literal("Random Augment Name Example");
        }

        @Override
        public void onPress() {
        }

        @Override
        protected void renderExtra(DrawContext context) {
            context.drawItem(augmentItem, xCenter - 8, yCenter - 8);
        }

        @Override
        protected MutableText getNarrationMessage() {
            return getAugmentName();
        }
    }
}
