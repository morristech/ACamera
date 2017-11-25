package com.xiaopo.flying.acamera;

import android.util.Size;
import android.view.Surface;

import com.xiaopo.flying.acamera.base.Lifetime;
import com.xiaopo.flying.acamera.command.CameraCommandFactory;
import com.xiaopo.flying.acamera.focus.FocusTrigger;
import com.xiaopo.flying.acamera.preview.Camera2PreviewSizeSelector;
import com.xiaopo.flying.acamera.preview.PreviewSizeSelector;
import com.xiaopo.flying.acamera.preview.PreviewStarter;

import io.reactivex.subjects.BehaviorSubject;

/**
 * @author wupanjie
 */
class ARealCamera implements ACamera {

  private final Lifetime lifetime;
  private final PreviewStarter previewStarter;
  private final ACameraCharacteristics characteristics;
  private final PreviewSizeSelector previewSizeSelector;

  private final FocusTrigger focusTrigger;

  private Surface currentPreviewSurface;

  ARealCamera(Lifetime lifetime,
              ACameraCharacteristics characteristics,
              PreviewStarter previewStarter,
              FocusTrigger focusTrigger) {
    this.lifetime = lifetime;
    this.characteristics = characteristics;
    this.previewStarter = previewStarter;
    this.previewSizeSelector = new Camera2PreviewSizeSelector(characteristics.getSupportedPreviewSizes());
    this.focusTrigger = focusTrigger;
  }

  @Override
  public ACameraCharacteristics getCharacteristic() {
    return characteristics;
  }


  @Override
  public void startPreview(final Surface previewSurface) {
    currentPreviewSurface = previewSurface;

    previewStarter.setPreviewSurface(previewSurface);
    previewStarter
        .startPreview()
        .subscribe();
  }

  @Override
  public Size pickPreviewSize(Size imageResolution) {
    return previewSizeSelector.pickPreviewSize(imageResolution);
  }

  @Override
  public void triggerFocusAt(float x, float y) {
    focusTrigger.triggerFocusAt(x, y);
  }

  @Override
  public void close() {
    lifetime.close();
    currentPreviewSurface.release();
    currentPreviewSurface = null;
  }
}
