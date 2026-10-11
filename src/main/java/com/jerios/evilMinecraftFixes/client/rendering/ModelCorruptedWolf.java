package com.jerios.evilMinecraftFixes.client.rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCorruptedWolf extends ModelBase {
    private final ModelRenderer head;
    private final ModelRenderer mane;
    private final ModelRenderer body;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer leg4;
    private final ModelRenderer tail;
    private final ModelRenderer muscles;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer muscles2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer teeth;
    private final ModelRenderer noze_r1;
    private final ModelRenderer noze_r2;
    private final ModelRenderer teeth2;
    private final ModelRenderer noze_r3;
    private final ModelRenderer noze_r4;
    private final ModelRenderer teeth3;
    private final ModelRenderer teeth4;
    private final ModelRenderer noze_r5;
    private final ModelRenderer noze_r6;
    private final ModelRenderer nose;
    private final ModelRenderer eyes;

    public ModelCorruptedWolf() {
        textureWidth = 64;
        textureHeight = 32;

        head = new ModelRenderer(this);
        head.setRotationPoint(-1.0F, 13.5F, -7.0F);
        head.cubeList.add(new ModelBox(head, 0, 0, -2.0F, -5.0F, -2.0F, 6, 7, 4, 0.0F));
        head.cubeList.add(new ModelBox(head, 16, 14, 2.0F, -7.0F, 0.0F, 2, 2, 1, 0.0F));
        head.cubeList.add(new ModelBox(head, 16, 14, -2.0F, -7.0F, 0.0F, 2, 2, 1, 0.0F));
        head.cubeList.add(new ModelBox(head, 16, 14, -1.5F, -8.0F, 0.0F, 1, 2, 1, 0.0F));
        head.cubeList.add(new ModelBox(head, 16, 14, 2.5F, -9.0F, 0.0F, 1, 2, 1, 0.0F));
        head.cubeList.add(new ModelBox(head, -3, 7, -0.5F, -1.77F, -8.0F, 3, 2, 7, 0.0F));
        head.cubeList.add(new ModelBox(head, 0, 0, -1.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F));
        head.cubeList.add(new ModelBox(head, -3, -3, -1.0F, 1.5F, -7.0F, 4, 1, 5, 0.0F));

        mane = new ModelRenderer(this);
        mane.setRotationPoint(-1.0F, 14.0F, -3.0F);
        setRotationAngle(mane, 1.5708F, 0.0F, 0.0F);
        mane.cubeList.add(new ModelBox(mane, 21, 0, -3.0F, -3.0F, -3.0F, 8, 6, 7, 0.0F));
        mane.cubeList.add(new ModelBox(mane, -5, -5, -3.0F, 6.0F, -3.0F, 1, 2, 7, 0.0F));
        mane.cubeList.add(new ModelBox(mane, -4, -5, 4.0F, 6.0F, -3.0F, 1, 2, 7, 0.0F));
        mane.cubeList.add(new ModelBox(mane, -1, 0, -2.0F, 5.0F, 2.0F, 6, 6, 2, 0.0F));

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 14.0F, 2.0F);
        setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
        body.cubeList.add(new ModelBox(body, 18, 14, -3.0F, -2.0F, -3.0F, 6, 9, 6, 0.0F));

        leg1 = new ModelRenderer(this);
        leg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
        leg1.cubeList.add(new ModelBox(leg1, 0, 18, 0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F));

        leg2 = new ModelRenderer(this);
        leg2.setRotationPoint(0.5F, 16.0F, 7.0F);
        leg2.cubeList.add(new ModelBox(leg2, 0, 18, 0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F));

        leg3 = new ModelRenderer(this);
        leg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
        leg3.cubeList.add(new ModelBox(leg3, 0, 18, 0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F));

        leg4 = new ModelRenderer(this);
        leg4.setRotationPoint(0.5F, 16.0F, -4.0F);
        leg4.cubeList.add(new ModelBox(leg4, 0, 18, 0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F));

        tail = new ModelRenderer(this);
        tail.setRotationPoint(-1.0F, 12.0F, 10.0F);
        tail.cubeList.add(new ModelBox(tail, 9, 18, 0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F));

        muscles = new ModelRenderer(this);
        muscles.setRotationPoint(2.5F, 17.0F, 3.0F);
        muscles.cubeList.add(new ModelBox(muscles, 2, 1, 0.0F, -5.0F, 0.25F, 1, 5, 1, 0.0F));

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, -3.0F, -1.0F);
        muscles.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.2182F, 0.0F, 0.0F);
        cube_r1.cubeList.add(new ModelBox(cube_r1, 1, 0, 0.0F, -3.0F, -1.0F, 1, 3, 2, 0.0F));

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.0F, 0.0F, 4.5F);
        muscles.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.2182F, 0.0F, 0.0F);
        cube_r2.cubeList.add(new ModelBox(cube_r2, 4, 0, 0.0F, -6.0F, -1.0F, 1, 6, 2, 0.0F));

        muscles2 = new ModelRenderer(this);
        muscles2.setRotationPoint(-3.5F, 17.0F, 3.0F);
        muscles2.cubeList.add(new ModelBox(muscles2, 1, 0, 0.0F, -4.0F, 4.0F, 1, 3, 2, 0.0F));

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(0.0F, 0.0F, -1.0F);
        muscles2.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.2182F, 0.0F, 0.0F);
        cube_r3.cubeList.add(new ModelBox(cube_r3, 0, -1, 0.0F, -5.0F, -1.0F, 1, 5, 3, 0.0F));

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(0.0F, 0.0F, 3.0F);
        muscles2.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.2182F, 0.0F, 0.0F);
        cube_r4.cubeList.add(new ModelBox(cube_r4, 1, 0, 0.0F, -5.0F, -1.0F, 1, 5, 2, 0.0F));

        teeth = new ModelRenderer(this);
        teeth.setRotationPoint(0.75F, 16.25F, -13.0F);


        noze_r1 = new ModelRenderer(this);
        noze_r1.setRotationPoint(-0.25F, -1.0F, 0.0F);
        teeth.addChild(noze_r1);
        setRotationAngle(noze_r1, -0.1309F, 0.0F, 0.0F);
        noze_r1.cubeList.add(new ModelBox(noze_r1, 2, 1, 0.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F));

        noze_r2 = new ModelRenderer(this);
        noze_r2.setRotationPoint(-2.25F, -1.25F, 0.0F);
        teeth.addChild(noze_r2);
        setRotationAngle(noze_r2, -0.1309F, 0.0F, 0.0F);
        noze_r2.cubeList.add(new ModelBox(noze_r2, 2, 1, 0.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F));

        teeth2 = new ModelRenderer(this);
        teeth2.setRotationPoint(0.75F, 16.25F, -11.5F);


        noze_r3 = new ModelRenderer(this);
        noze_r3.setRotationPoint(-1.25F, -1.0F, 0.0F);
        teeth2.addChild(noze_r3);
        setRotationAngle(noze_r3, -0.1309F, 0.0F, 0.0F);
        noze_r3.cubeList.add(new ModelBox(noze_r3, 2, 1, 0.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F));
        noze_r3.cubeList.add(new ModelBox(noze_r3, 2, 1, -1.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F));

        noze_r4 = new ModelRenderer(this);
        noze_r4.setRotationPoint(0.0F, -0.5F, 0.0F);
        teeth2.addChild(noze_r4);
        setRotationAngle(noze_r4, -0.1309F, 0.0F, 0.0F);
        noze_r4.cubeList.add(new ModelBox(noze_r4, 2, 1, 0.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F));

        teeth3 = new ModelRenderer(this);
        teeth3.setRotationPoint(0.75F, 16.25F, -10.25F);


        teeth4 = new ModelRenderer(this);
        teeth4.setRotationPoint(0.75F, 16.25F, -9.25F);


        noze_r5 = new ModelRenderer(this);
        noze_r5.setRotationPoint(-0.25F, -0.5F, 0.0F);
        teeth4.addChild(noze_r5);
        setRotationAngle(noze_r5, -0.1309F, 0.0F, 0.0F);
        noze_r5.cubeList.add(new ModelBox(noze_r5, 2, 1, 0.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F));

        noze_r6 = new ModelRenderer(this);
        noze_r6.setRotationPoint(-2.25F, -1.0F, 0.0F);
        teeth4.addChild(noze_r6);
        setRotationAngle(noze_r6, -0.1309F, 0.0F, 0.0F);
        noze_r6.cubeList.add(new ModelBox(noze_r6, 2, 1, 0.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F));

        nose = new ModelRenderer(this);
        nose.setRotationPoint(0.0F, 13.75F, -13.25F);
        nose.cubeList.add(new ModelBox(nose, 0, 0, -1.0F, -4.0F, -1.0F, 2, 2, 2, 0.0F));

        eyes = new ModelRenderer(this);
        eyes.setRotationPoint(2.0F, 12.75F, -9.5F);
        eyes.cubeList.add(new ModelBox(eyes, 3, 3, -1.0F, -4.0F, -1.0F, 2, 3, 2, 0.0F));
        eyes.cubeList.add(new ModelBox(eyes, 0, 0, -5.0F, -4.0F, -1.0F, 2, 3, 2, 0.0F));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        head.render(f5);
        mane.render(f5);
        body.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        tail.render(f5);
        muscles.render(f5);
        muscles2.render(f5);
        teeth.render(f5);
        teeth2.render(f5);
        teeth3.render(f5);
        teeth4.render(f5);
        nose.render(f5);
        eyes.render(f5);


    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
