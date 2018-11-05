package com.bignerdranch.android.recycolumbus;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class SearchFragment extends Fragment {

    private static final int REQUEST_PHOTO = 0;

    private static final String photoAuthority = "recycolumbus.fileprovider";

    private File mBarcodePhoto;
    private Button mScannerButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        mScannerButton = v.findViewById(R.id.search_scan_button);
        final Intent captureBarcode = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        mScannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = FileProvider.getUriForFile(getActivity(), photoAuthority, mBarcodePhoto);
                captureBarcode.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                List<ResolveInfo> cameraActivities = getActivity()
                        .getPackageManager()
                        .queryIntentActivities(captureBarcode, PackageManager.MATCH_DEFAULT_ONLY);

                for(ResolveInfo activity: cameraActivities) {
                    getActivity()
                            .grantUriPermission(activity.activityInfo.packageName, uri,
                                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }

                startActivityForResult(captureBarcode, REQUEST_PHOTO);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) {
            return;
        }

        if(requestCode == REQUEST_PHOTO) {
            Uri uri = FileProvider.getUriForFile(getActivity(), photoAuthority, mBarcodePhoto);

            getActivity().revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            Toast.makeText(getContext(), "Took a photo.", Toast.LENGTH_SHORT).show();
        }
    }
}
