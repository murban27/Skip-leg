// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "iosApp",
    platforms: [
        .iOS(.v16)
    ],
    products: [
        .library(
            name: "iosApp",
            targets: ["iosApp"]
        ),
    ],
    dependencies: [
        .package(path: "../shared")
    ],
    targets: [
        .target(
            name: "iosApp",
            dependencies: [
                .product(name: "Shared", package: "shared")
            ]
        ),
        .testTarget(
            name: "iosAppTests",
            dependencies: ["iosApp"]
        ),
    ]
)