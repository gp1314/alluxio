/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.dora.server.worker;

import alluxio.dora.client.file.FileSystem;
import alluxio.dora.client.file.FileSystemContext;
import alluxio.dora.client.fuse.AbstractFuseIntegrationTest;
import alluxio.conf.Configuration;
import alluxio.conf.PropertyKey;

/**
 * Integration tests for worker embedded Fuse application.
 */
public class WorkerFuseIntegrationTest extends AbstractFuseIntegrationTest {
  @Override
  public void configure() {
    Configuration.set(PropertyKey.WORKER_FUSE_ENABLED, true);
    Configuration.set(PropertyKey.FUSE_MOUNT_POINT, mMountPoint);
    Configuration.set(PropertyKey.FUSE_MOUNT_ALLUXIO_PATH, ALLUXIO_ROOT);
  }

  @Override
  public void mountFuse(FileSystemContext context,
      FileSystem fileSystem, String mountPoint, String alluxioRoot) {
    // Fuse application is mounted automatically by the worker
  }

  @Override
  public void beforeStop() throws Exception {
    // Fuse application is unmounted automatically when stopping the worker
  }

  @Override
  public void afterStop() throws Exception {
    // umount the mountpoint
    umountFromShellIfMounted();
  }
}