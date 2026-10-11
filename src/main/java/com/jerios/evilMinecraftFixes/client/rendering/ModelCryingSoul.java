package com.jerios.evilMinecraftFixes.client.rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCryingSoul extends ModelBase {
    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer hands;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;
    private final ModelRenderer cube_r9;
    private final ModelRenderer cube_r10;
    private final ModelRenderer eyes;
    private final ModelRenderer cube_r11;
    private final ModelRenderer cube_r12;
    private final ModelRenderer cube_r13;
    private final ModelRenderer cube_r14;
    private final ModelRenderer cube_r15;
    private final ModelRenderer cube_r16;
    private final ModelRenderer cube_r17;
    private final ModelRenderer cube_r18;

    public ModelCryingSoul() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(-2.0F, 24.0F, 0.0F);
        body.cubeList.add(new ModelBox(body, 26, 39, 4.0F, -5.0F, 0.0F, 2, 4, 2, 0.0F));
        body.cubeList.add(new ModelBox(body, 14, 37, -1.0F, -5.0F, -1.0F, 2, 3, 4, 0.0F));
        body.cubeList.add(new ModelBox(body, 0, 16, -1.0F, -11.0F, -1.0F, 7, 6, 4, 0.0F));
        body.cubeList.add(new ModelBox(body, 6, 10, -10.0F, -6.0F, -1.0F, 9, 2, 2, 0.0F));

        head = new ModelRenderer(this);
        head.setRotationPoint(-2.0F, 12.75F, 0.0F);
        head.cubeList.add(new ModelBox(head, 0, 0, -3.0F, -8.0F, -3.0F, 11, 8, 8, 0.0F));

        hands = new ModelRenderer(this);
        hands.setRotationPoint(-3.0F, 18.0F, -2.0F);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(5.0F, -8.0F, 10.0F);
        hands.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.1309F, 0.0F, 0.0F);
        cube_r1.cubeList.add(new ModelBox(cube_r1, 36, 23, -1.0F, -2.0F, -4.0F, 2, 2, 5, 0.0F));

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(1.0F, 0.0F, 6.0F);
        hands.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.1757F, -0.1739F, -0.0077F);
        cube_r2.cubeList.add(new ModelBox(cube_r2, 36, 16, -1.0F, -2.0F, -1.0F, 2, 2, 5, 0.0F));

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(1.0F, -9.0F, 7.0F);
        hands.addChild(cube_r3);
        setRotationAngle(cube_r3, -0.3604F, 0.2457F, -0.0914F);
        cube_r3.cubeList.add(new ModelBox(cube_r3, 2, 19, -1.0F, -2.0F, -1.0F, 2, 2, 5, 0.0F));

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(8.0F, -6.0F, 6.0F);
        hands.addChild(cube_r4);
        setRotationAngle(cube_r4, -0.3604F, 0.2457F, -0.0914F);
        cube_r4.cubeList.add(new ModelBox(cube_r4, 14, 35, -1.0F, -2.0F, -1.0F, 2, 2, 5, 0.0F));

        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(4.0F, -2.0F, 6.0F);
        hands.addChild(cube_r5);
        setRotationAngle(cube_r5, -0.3604F, 0.2457F, -0.0914F);
        cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 33, -1.0F, -2.0F, -1.0F, 2, 2, 5, 0.0F));

        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(6.0F, 0.0F, 0.0F);
        hands.addChild(cube_r6);
        setRotationAngle(cube_r6, -0.22F, -0.1278F, 0.0285F);
        cube_r6.cubeList.add(new ModelBox(cube_r6, 22, 16, -1.0F, -2.0F, -4.0F, 2, 2, 5, 0.0F));

        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(6.0F, 5.0F, 1.0F);
        hands.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.1871F, 0.1875F, 0.0351F);
        cube_r7.cubeList.add(new ModelBox(cube_r7, 14, 30, -1.0F, -2.0F, -4.0F, 2, 2, 5, 0.0F));

        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(3.0F, -1.0F, 1.0F);
        hands.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.2992F, -0.1412F, -0.1109F);
        cube_r8.cubeList.add(new ModelBox(cube_r8, 28, 30, -1.0F, -2.0F, -4.0F, 2, 2, 5, 0.0F));

        cube_r9 = new ModelRenderer(this);
        cube_r9.setRotationPoint(1.0F, 5.0F, 1.0F);
        hands.addChild(cube_r9);
        setRotationAngle(cube_r9, 0.2992F, -0.1412F, -0.1109F);
        cube_r9.cubeList.add(new ModelBox(cube_r9, 0, 26, -1.0F, -2.0F, -4.0F, 2, 2, 5, 0.0F));

        cube_r10 = new ModelRenderer(this);
        cube_r10.setRotationPoint(0.0F, 0.0F, 0.0F);
        hands.addChild(cube_r10);
        setRotationAngle(cube_r10, -0.176F, 0.1289F, -0.0229F);
        cube_r10.cubeList.add(new ModelBox(cube_r10, 9, 7, -1.0F, -2.0F, -4.0F, 2, 2, 5, 0.0F));

        eyes = new ModelRenderer(this);
        eyes.setRotationPoint(2.0F, 5.0F, -2.0F);
        eyes.cubeList.add(new ModelBox(eyes, 38, 10, -1.0F, 1.0F, 6.0F, 2, 2, 3, 0.0F));
        eyes.cubeList.add(new ModelBox(eyes, 38, 10, -7.0F, 1.0F, 7.0F, 2, 2, 3, 0.0F));
        eyes.cubeList.add(new ModelBox(eyes, 38, 10, -7.0F, 6.0F, 7.0F, 2, 2, 3, 0.0F));
        eyes.cubeList.add(new ModelBox(eyes, 14, 26, -6.0F, 11.0F, -1.0F, 2, 2, 2, 0.0F));
        eyes.cubeList.add(new ModelBox(eyes, 38, 0, -5.0F, -2.0F, 1.0F, 2, 2, 3, 0.0F));
        eyes.cubeList.add(new ModelBox(eyes, 38, 5, 1.0F, -2.0F, 1.0F, 2, 2, 3, 0.0F));

        cube_r11 = new ModelRenderer(this);
        cube_r11.setRotationPoint(3.0F, 5.0F, -1.0F);
        eyes.addChild(cube_r11);
        setRotationAngle(cube_r11, 0.0F, 1.2217F, 0.0F);
        cube_r11.cubeList.add(new ModelBox(cube_r11, 38, 10, -1.0F, -2.0F, -2.0F, 2, 2, 3, 0.0F));

        cube_r12 = new ModelRenderer(this);
        cube_r12.setRotationPoint(-8.0F, 3.0F, 5.0F);
        eyes.addChild(cube_r12);
        setRotationAngle(cube_r12, 0.0F, 1.2217F, 0.0F);
        cube_r12.cubeList.add(new ModelBox(cube_r12, 38, 10, -1.0F, -2.0F, -2.0F, 2, 2, 3, 0.0F));

        cube_r13 = new ModelRenderer(this);
        cube_r13.setRotationPoint(-8.0F, 5.0F, 3.0F);
        eyes.addChild(cube_r13);
        setRotationAngle(cube_r13, 0.0F, 1.309F, 0.0F);
        cube_r13.cubeList.add(new ModelBox(cube_r13, 38, 10, -1.0F, -2.0F, -2.0F, 2, 2, 3, 0.0F));

        cube_r14 = new ModelRenderer(this);
        cube_r14.setRotationPoint(-8.0F, 5.0F, -1.0F);
        eyes.addChild(cube_r14);
        setRotationAngle(cube_r14, 0.0F, 1.4835F, 0.0F);
        cube_r14.cubeList.add(new ModelBox(cube_r14, 38, 10, -1.0F, -2.0F, -2.0F, 2, 2, 3, 0.0F));

        cube_r15 = new ModelRenderer(this);
        cube_r15.setRotationPoint(-2.0F, 5.0F, -1.0F);
        eyes.addChild(cube_r15);
        setRotationAngle(cube_r15, 0.0F, 1.2217F, 0.0F);
        cube_r15.cubeList.add(new ModelBox(cube_r15, 38, 10, -1.0F, -2.0F, -2.0F, 2, 2, 3, 0.0F));

        cube_r16 = new ModelRenderer(this);
        cube_r16.setRotationPoint(5.0F, 5.0F, 2.0F);
        eyes.addChild(cube_r16);
        setRotationAngle(cube_r16, 0.0F, 1.309F, 0.0F);
        cube_r16.cubeList.add(new ModelBox(cube_r16, 38, 10, -1.0F, -2.0F, -2.0F, 2, 2, 3, 0.0F));

        cube_r17 = new ModelRenderer(this);
        cube_r17.setRotationPoint(5.0F, 3.0F, 7.0F);
        eyes.addChild(cube_r17);
        setRotationAngle(cube_r17, 0.0F, 1.309F, 0.0F);
        cube_r17.cubeList.add(new ModelBox(cube_r17, 38, 10, -1.0F, -2.0F, -2.0F, 2, 2, 3, 0.0F));

        cube_r18 = new ModelRenderer(this);
        cube_r18.setRotationPoint(0.0F, 0.0F, 0.0F);
        eyes.addChild(cube_r18);
        setRotationAngle(cube_r18, 0.0F, 1.9635F, 0.0F);
        cube_r18.cubeList.add(new ModelBox(cube_r18, 38, 10, -1.0F, -2.0F, -2.0F, 2, 2, 3, 0.0F));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        body.render(f5);
        head.render(f5);
        hands.render(f5);
        eyes.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
